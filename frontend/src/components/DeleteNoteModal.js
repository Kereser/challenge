import React from 'react'
import noteService from '../services/note'
import { useListStore } from '../state/generalState';

export default function DeleteNoteModal({note}) {
  const deleteNote = useListStore(s => s.deleteNote)
  const setFilter = useListStore(s => s.setFilter)
  const setList = useListStore(s => s.setList)

  const deleteNoteBtn = async (e) => {
    e.preventDefault();

    try {
      await noteService.deleteNote(note.id) 
      deleteNote(note.id)
      const newList = await noteService.getInitialNotes()
      setList(newList)
      setFilter("All")
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <>
      <button type="button" className="btn btn-danger p-1 ms-1" data-bs-toggle="modal" data-bs-target={`#deleteModal${note.id}`}>
        delete
      </button>

      <div className="modal fade" id={`deleteModal${note.id}`} tabIndex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
        <div className="modal-dialog">
          <div className="modal-content">

            <div className="modal-header">
              <h1 className="modal-title fs-5" id="deleteModalLabel">Deleting a note</h1>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div className="modal-body">
              <p>Are u sure you want to delete this note?</p>
            </div>

            <div className="modal-footer">
              <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="button" className="btn btn-danger" data-bs-dismiss="modal" onClick={deleteNoteBtn}>Delete</button>
            </div>
          </div>
        </div>
      </div>

    </>
  )
}
