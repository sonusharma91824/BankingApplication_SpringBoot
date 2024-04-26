import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AccountServices from '../service/AccountServices';
import AddAccountComponent from './AddAccountComponent';
import { useParams } from 'react-router-dom';

const ListAccountHolders = () => {
    const [accounts, setAccounts] = useState([]);
    const [accountHolderName, setAccountHolderName] = useState("");
    const [balance, setBalance] = useState("");
    const [id,setId] = useState();
    const [editName ,setEditName] =useState("");
    const [editAmount, setEditAmount] = useState("");
    useEffect(() => {
        AccountServices.getAllAccounts()
            .then((response) => {
                setAccounts(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, [accountHolderName,balance,id]);


         const save = async(event) =>{
            event.preventDefault(); // Prevent the default form submission behavior
            try {
                await axios.post("http://localhost:8080/account/save", {
                    accountHolderName: accountHolderName,
                    balance: balance
                });
                alert("New Account Holder Added Successfully");
                // Clear input fields after successful submission
                setAccountHolderName("");
                setBalance("");
            } catch (error) {
                alert("Something went wrong, account could not be added.");
            }
        }

        const handleDelete=async(id)=>{
            id.preventDefault(); // Prevent the default form submission behavior

            try{
           await axios.delete(`http://localhost:8080/account/${id}`);
           alert("account deleted succesfully")
           setId(id)
        }catch{
            alert("something went wrong, account could not deleted")
        }
    }
    
    const handleUpdate = async (id) => {
        console.log();
    
        // try {
        //     await axios.put(`http://localhost:8080/account/${id}`, {
        //         name: accountHolderName,
        //         amount: balance
        //     });
        //     alert("Account updated successfully");
        //     setId(id);
        // } catch (error) {
        //     console.error("Error updating account:", error);
        //     alert("Something went wrong. Could not update account holder's details.");
        // }
    }
    

    return (
        <div>
             <div className="Container">
                <form onSubmit={save}>
                    <div className="form-group">
                        <label>Account Holder Name</label>
                        <input type="text" className="form-control" placeholder="Enter account holder name"
                            value={accountHolderName}
                            onChange={(event) => setAccountHolderName(event.target.value)} />
                    </div>
                    <div className="form-group">
                        <label>Balance</label>
                        <input type="number" className="form-control" placeholder="Enter balance"
                            value={balance}
                            onChange={(event) => setBalance(event.target.value)} />
                    </div>
                    <button type="submit" className="btn btn-primary mt-4">Add Account Holder</button>
                </form>
            </div>
            <table className="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Account Id</th>
                        <th>Account Holder Name</th>
                        <th>Account Balance</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {accounts.map(account => (
                        <tr key={account.id}>
                            <td>{account.id}</td>
                            <td>{account.accountHolderName}</td>
                            <td>{account.balance}</td>
                            <td>
                                <button className='btn btn-success' onClick={() => handleUpdate(account.id)}>Update</button>
                                <button className='btn btn-danger ml-2' onClick={() => handleDelete(account.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListAccountHolders;
