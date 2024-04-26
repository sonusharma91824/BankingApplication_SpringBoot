
import './App.css';
import AccountServices from './service/AccountServices';
import HeaderComponents from './components/HeaderComponents';
import ListAccountHolders from './components/ListAccountHolders';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AddAccountComponent from './components/AddAccountComponent';
   function App() {
        return (
          <div>
              <Router>
                  <HeaderComponents/>
                  <div className="container">
                        <Routes>
                        <Route path="/" index Component={ListAccountHolders}></Route>                        
                        {/* <Route path="/accounts" Component={ListAccountHolders}></Route>  */}
                         <Route path="/add-Account" Component={AddAccountComponent}></Route>
                        </Routes>
   
                  </div>
              </Router>
          </div>
  );
}

export default App;
