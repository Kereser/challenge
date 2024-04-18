import React from 'react'
import { useListStore } from '../state/generalState'
import noteService from '../services/note'

export default function UnArchiveNote({note}) {
  const updateList = useListStore(s => s.updateList)

  const unArchiveBtn = async () => {
    const updateNote = {
      ...note, 
      archived: false
    }

    try {
      const resNote = await noteService.updateNote(updateNote.id, updateNote)
      updateList(resNote)
    } catch (error) {
      console.error(error)
    }
  }

  return (
    <>
      <button type="button" className="btn btn-danger p-1 me-1" onClick={unArchiveBtn}>
        Un archive
      </button>
    </>
  )
}
