import axios from 'axios';
const BASE_URL = '/notes'

const getInitialNotes = async (filter) => {
  const res = await axios.get(`${BASE_URL}`, {params: {filter}});
  return res.data;
}

const deleteNote = async (noteId) => {
  const res = await axios.delete(`${BASE_URL}/${noteId}`)
  return res
}

const updateNote = async (noteId, body) => {
  const res = await axios.put(`${BASE_URL}/${noteId}`, body)
  return res.data;
}

const createNote = async (body) => {
  const res = await axios.post(`${BASE_URL}`, body)
  return res.data;
}

const createCategory = async (noteId, body) => {
  const res = await axios.post(`${BASE_URL}/${noteId}/categories`, body)
  return res.data;
}

// eslint-disable-next-line import/no-anonymous-default-export
export default { getInitialNotes, deleteNote, updateNote, createNote, createCategory }