import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import FormValidator from './FormValidator';

class Create extends Component {

  constructor() {
    super();
	
	    this.validator = new FormValidator([
      { 
        field: 'emailAddress', 
        method: 'isEmpty', 
        validWhen: false, 
        message: 'Email is required.' 
      },
      { 
        field: 'emailAddress',
        method: 'isEmail', 
        validWhen: true, 
        message: 'That is not a valid email.'
      },
      { 
        field: 'lastName', 
        method: 'isEmpty', 
        validWhen: false, 
        message: 'Pleave provide a Last Name'
      },
      { 
        field: 'firstName', 
        method: 'isEmpty', 
        validWhen: false, 
        message: 'Pleave provide a First Name'
      }       
    ]);
	
    this.state = {
	  emailAddress: '',
      lastName: '',
      firstName: '',
      address: '',
	  validation: this.validator.valid()
    };
  }
  onChange = (e) => {
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }
  
  onSubmit = (e) => {
    e.preventDefault();
	const validation = this.validator.validate(this.state);
    this.setState({ validation });
    this.submitted = true;

    if (validation.isValid) {
		const { emailAddress,lastName, firstName, address } = this.state;

		axios.post('http://localhost:8080/customers', { emailAddress,lastName, firstName, address})
			.then((result) => {
				this.props.history.push("/list")
			});
    }

  }

  render() {
    let validation = this.submitted ?                         // if the form has been submitted at least once
                      this.validator.validate(this.state) :   // then check validity every time we render
                      this.state.validation                   // otherwise just use what's in state
					  
    const { emailAddress, lastName, firstName, address } = this.state;
    return (
      <div className="container">
        <div className="panel panel-default">
          <div className="panel-heading">
            <h3 className="panel-title">
              New Customer
            </h3>
          </div>
          <div className="panel-body">
            
            <form onSubmit={this.onSubmit}>
			  <div className="form-group">
                <label>Username:</label>
                <input type="email" className="form-control" name="emailAddress" value={emailAddress} onChange={this.onChange} placeholder="sample@email.com" />
				<span className="help-block">{validation.emailAddress.message}</span>
              </div>
              <div className="form-group">
                <label>Last Name:</label>
                <input type="text" className="form-control" name="lastName" value={lastName} onChange={this.onChange} placeholder="Last Name" />
				<span className="help-block">{validation.lastName.message}</span>
              </div>
              <div className="form-group">
                <label>First Name:</label>
                <input type="text" className="form-control" name="firstName" value={firstName} onChange={this.onChange} placeholder="First Name" />
				<span className="help-block">{validation.firstName.message}</span>
              </div>
              <div className="form-group">
                <label>Address:</label>
                <input type="text" className="form-control" name="address" value={address} onChange={this.onChange} placeholder="Address" />
              </div>
              <button type="submit" className="btn btn-default" >Create</button>
			  
			  <h5><Link to="/list"> Back to Customer List</Link></h5>
            </form>
          </div>
        </div>
      </div>
    );
  }
}

export default Create;