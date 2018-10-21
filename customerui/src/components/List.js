import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

class List extends Component {


  constructor(props) {
	console.log('process.argv', process.argv.slice(2));
    super(props);
    this.state = {
      customerList: []
    };
  }

  componentDidMount() {
    axios.get('http://localhost:8080/customers')
      .then(res => {
		const customerList = res.data;
        this.setState({ customerList });
      });
  }
  
   delete(id){
    axios.delete('http://localhost:8080/customers/'+id)
      .then((result) => {
        this.props.history.push("/")
      });
  }

  render() {
    return (
      <div className="container">
          <div className="panel-heading">
            <h3 className="panel-title">
              Customer List
            </h3>
          </div>
          <div className="panel-body">
            <h4><Link to="/create"> Add Customer</Link></h4>
            <table className="table table-striped">
              <thead>
                <tr>
				  <th>Username</th>
                  <th>Last Name</th>
                  <th>First Name</th>
                  <th>Address</th>
				  <th></th>
				  <th></th>
                </tr>
              </thead>
              <tbody>
                {this.state.customerList.map(c =>
                  <tr>
				  	<td>{c.emailAddress}</td>
                    <td><Link to={`/view/${c.emailAddress}`}>{c.lastName}</Link></td>
                    <td>{c.firstName}</td>
                    <td>{c.address}</td>
					<td><Link to={`/edit/${c.emailAddress}`} className="btn btn-success">Edit</Link>&nbsp;</td>
					<td><button onClick={this.delete.bind(this, c.emailAddress)} className="btn btn-danger">Delete</button></td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
      </div>
    );
  }
}

export default List;