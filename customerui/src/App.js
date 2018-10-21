import React, { Component } from 'react';
import './App.css';
import { Link } from 'react-router-dom';

class App extends Component {
	render() {
		return (
		<div className="App">
			<header className="App-header">          
				<p>
					Welcome to Customer Repo!
				</p>
				<h4><Link to="/list"> View Customers</Link></h4>
			</header>
		</div>
		);
	}
}

export default App;
