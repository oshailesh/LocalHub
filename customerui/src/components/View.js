import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Show extends Component {

  constructor(props) {
    super(props);
    this.state = {
      customer: {}
    };
  }

  componentDidMount() {
    axios.get('http://localhost:8080/customers/'+this.props.match.params.id)
      .then(res => {
        this.setState({ customer: res.data });
        console.log(this.state.customer);
      });
  }

  delete(id){
    console.log(id);
    axios.delete('http://localhost:8080/customers/'+id)
      .then((result) => {
        this.props.history.push("/list")
      });
  }

  render() {
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
              Customer Details
            </h3>
          </div>
          <div className="panel-body">

            <dl>
			  <dt>Username:</dt>
              <dd>{this.state.customer.emailAddress}</dd>
              <dt>Last Name:</dt>
              <dd>{this.state.customer.lastName}</dd>
              <dt>First Name:</dt>
              <dd>{this.state.customer.firstName}</dd>
              <dt>Address:</dt>
              <dd>{this.state.customer.address}</dd>
            </dl>
			<h4><Link to="/list"> Back to Customer List</Link></h4>
          </div>
        </div>
      </div>
    );
  }
}

export default Show;