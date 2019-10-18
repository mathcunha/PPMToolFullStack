import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from "./types";

export const createProject = (project, history) => async dispatch => {
  try {
    //const res = await axios.post("http://localhost:8090/api/project", project);
    await axios.post("http://localhost:8090/api/project", project);
    history.push("/dashboard");
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const getProjects = () => async dispatch => {
  const res = await axios.get("http://localhost:8090/api/project");
  dispatch({
    type: GET_PROJECTS,
    payload: res.data
  });
};

export const getProject = (identifier, history) => async dispatch => {
  try {
    const res = await axios.get(
      `http://localhost:8090/api/project/${identifier}`
    );
    dispatch({
      type: GET_PROJECT,
      payload: res.data
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const deleteProject = (identifier, history) => async dispatch => {
  try {
    await axios.delete(`http://localhost:8090/api/project/${identifier}`);
    dispatch({
      type: DELETE_PROJECT,
      payload: identifier
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};
