import React, { useState } from 'react'
import CategoryList from './CategoryList'
import noteService from '../services/note'
import { useListStore } from '../state/generalState'

export default function CreateNoteModal() {
  const [title, setTitle] = useState("")
  const [content, setContent] = useState("")
  const [categories, setCategories] = useState([])
  const [categoryInput, setCategoryInput] = useState("")

  const setFilter = useListStore(s => s.setFilter)
  const addNewNote = useListStore(s => s.addNewNote)
  const setList = useListStore(s => s.setList)

  const createNewNote = async (e) => {
    e.preventDefault()

    try {
      const newNote = await noteService.createNote({
        title, content, archived: false
      })
      addNewNote(newNote)

      if (categories.length > 0) {
        for (const ctg of categories) {
          try {
            await noteService.createCategory(newNote.id, {name: ctg})
          } catch (error) {
            console.error(error)
          }
        }
      }

      setTitle("")
      setContent("")
      setCategories("")
      setCategoryInput("")
      
    } catch (error) {
      console.error(error)
    }
    
    try {
      const newUpdatedList = await noteService.getInitialNotes()
      setList(newUpdatedList)
      setFilter("All")
    } catch (error) {
      console.error(error)
    }
  }

  const addNewCategory = (e) => {
    e.preventDefault()
    setCategoryInput("")

    setCategories([...categories,  categoryInput.trim()])
  }

  return (
  <>
    <button type="button" className="btn btn-success p-1" data-bs-toggle="modal" data-bs-target="#createModal">
      Create Note
    </button>

    <div className="modal fade" id="createModal" tabIndex="-1" aria-labelledby="createModalLabel" aria-hidden="true">
    <div className="modal-dialog">
      <div className="modal-content">
        <div className="modal-header">
          <h1 className="modal-title fs-5" id="createModalLabel">Create New Note</h1>
          <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div className="modal-body">
          
        <form>
          <div className="mb-3">
            <label htmlFor="titleInput" className="form-label">Title</label>
            <input 
              className="form-control" 
              id="titleInput" 
              aria-describedby="titleHelp"
              value={title}
              onChange={e => setTitle(e.target.value)}
            />
            <div id="titleHelp" className="form-text">Set new title</div>
          </div>

          <div className="mb-3">
            <label htmlFor="contentInput" className="form-label">Content</label>
            <input 
              className="form-control" 
              id="contentInput" 
              aria-describedby="contentHelp"
              value={content}
              onChange={e => setContent(e.target.value)}
            />
            <div id="contentHelp" className="form-text">Create new content</div>
          </div>

          <div className="mb-0">
            <label htmlFor="categoriesInput" className="form-label">Categories</label>
            <div className="row row-cols-1 gx-4 gy-2 mx-0 border rounded py-2">
              {categories?.length > 0 ? <CategoryList categories={categories} setCategories={setCategories} />: "Upload ur categories."}
            </div>

            <div className='d-flex align-items-center justify-content-between py-2'>
              <div>
                <input 
                  className="form-control mr-2" 
                  id="categoriesInput"
                  value={categoryInput}
                  onChange={e => setCategoryInput(e.target.value)}
                />
              </div>
              <button type="button" className="btn btn-warning btn-sm" onClick={addNewCategory}>Add new Category.</button>
            </div>
          </div>

        </form>

        </div>
        <div className="modal-footer">
          <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" className="btn btn-success" data-bs-dismiss="modal" onClick={createNewNote}>Create</button>
        </div>
      </div>
    </div>
    </div>
  </>
  )
}
