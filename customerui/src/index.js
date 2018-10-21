import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import App from './App';
import './App.css';
import List from './components/List';
import Edit from './components/Edit';
import Create from './components/Create';
import View from './components/View';

ReactDOM.render(
  <Router>
      <div>
        <Route exact path='/' component={App} />
		<Route path='/list/' component={List} />
        <Route path='/edit/:id' component={Edit} />
        <Route path='/create' component={Create} />
        <Route path='/view/:id' component={View} />
      </div>
  </Router>,
  document.getElementById('root')
);