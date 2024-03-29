import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { deleteProject } from "../../actions/projectActions";

class ProjectItem extends Component {
  handleDeleteClick = identifier => {
    this.props.deleteProject(identifier);
  };

  render() {
    return (
      <div className="container">
        <div className="card card-body bg-light mb-3">
          <div className="row">
            <div className="col-2">
              <span className="mx-auto">{this.props.project.identifier}</span>
            </div>
            <div className="col-lg-6 col-md-4 col-8">
              <h3>{this.props.project.name}</h3>
              <p>{this.props.project.description}</p>
            </div>
            <div className="col-md-4 d-none d-lg-block">
              <ul className="list-group">
                <Link to={`/updateProject/${this.props.project.identifier}`}>
                  <li className="list-group-item update">
                    <i className="fa fa-edit pr-1">Update Project Info</i>
                  </li>
                </Link>

                <li
                  className="list-group-item delete"
                  onClick={this.handleDeleteClick.bind(
                    this,
                    this.props.project.identifier
                  )}
                >
                  <i className="fa fa-minus-circle pr-1"> Delete Project</i>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

ProjectItem.propTypes = {
  deleteProject: PropTypes.func.isRequired
};

export default connect(
  null,
  { deleteProject }
)(ProjectItem);
