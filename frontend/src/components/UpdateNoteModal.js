import React, { useEffect, useState } from 'react'
import CategoryList from './CategoryList'
import noteService from '../services/note'
import categoryService from '../services/category'
import { useListStore } from '../state/generalState'

export default function UpdateNoteModal({note}) {
  const [title, setTitle] = useState(note.title)
  const [content, setContent] = useState(note.content)
  const [categories, setCategories] = useState()
  const [categoryInput, setCategoryInput] = useState("")

  const updateList = useListStore(s => s.updateList)
  const setList = useListStore(s => s.setList)
  const setFilter = useListStore(s => s.setFilter)

  
  useEffect(() => {
    setCategories(note.categories?.map(ctg => ctg.name) || [])
  }, [note])
  
  const updateNoteBtn = async () => {
    let resNote;
    const categoriesToAdd = categories.filter(cName => !note.categories.map(c => c.name).includes(cName)) 
    const categoriesToDelete = note.categories.filter(ctg => !categories.includes(ctg.name))
    const updatedNote = {
      ...note,
      title,
      content,
    }

    try {
      resNote = await noteService.updateNote(note.id, updatedNote)
      updateList(resNote)
    } catch (error) {
      console.error(error)
      setTitle(note.title)
    }

    if (categoriesToAdd.length > 0) {
      for (const ctg of categoriesToAdd) {
        try {
          await noteService.createCategory(resNote.id, {name: ctg})
        } catch (error) {
          console.error(error)
        }
      }
    }

    if (categoriesToDelete.length > 0) {
      for (const ctg of categoriesToDelete) {
        try {
          await categoryService.deleteCategory(ctg.id)
        } catch (error) {
          console.error(error)
        }
      }
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
    
    setCategories([...categories,  categoryInput.trim()])
    setCategoryInput("")
  }

  return (
  <>
    <button type="button" className="btn btn-primary p-1" data-bs-toggle="modal" data-bs-target={`#updateModal${note.id}`}>
      Update
    </button>

    <div className="modal fade" id={`updateModal${note.id}`} tabIndex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
    <div className="modal-dialog">
      <div className="modal-content">
        <div className="modal-header">
          <h1 className="modal-title fs-5" id="updateModalLabel">Updating a note</h1>
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
            <div id="titleHelp" className="form-text">Update title here!</div>
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
            <div id="contentHelp" className="form-text">Update content here!</div>
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
          <button type="button" className="btn btn-primary"data-bs-dismiss="modal" onClick={updateNoteBtn}>Save changes</button>
        </div>
      </div>
    </div>
    </div>
  </>
  )
}
