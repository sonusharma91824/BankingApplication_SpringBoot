import axios from "axios";
const ACCOUNT_BASE_REST_API_URL='http://localhost:8080/account/getall';
class AccountService{
    getAllAccounts(){
        return axios.get(ACCOUNT_BASE_REST_API_URL)
    }
}
export default new AccountService