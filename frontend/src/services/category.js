import axios from "axios";
const BASE_URL = "/categories"

const deleteCategory = async (categoryId) => {
  return await axios.delete(`${BASE_URL}/${categoryId}`) 
} 

// eslint-disable-next-line import/no-anonymous-default-export
export default { deleteCategory }