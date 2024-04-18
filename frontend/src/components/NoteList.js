import React from 'react'
import NoteItem from './NoteItem'
import UpdateNoteModal from './UpdateNoteModal'
import DeleteNoteModal from './DeleteNoteModal'
import { useLocation } from 'react-router-dom'
import ArchiveNote from './ArchiveNote'
import UnArchiveNote from './UnArchiveNote'

export default function NoteList({notes}) {
  const {search} = useLocation()

  const myNotes = notes?.filter(note => note.archived === false)
  const archivedNotes = notes?.filter(note => note.archived === true)
  
  let notesToUse = []

  

  if (search !== "?archived=true") {
    notesToUse = myNotes
    return (
      <div className="row row-cols-4 gx-4 gy-2">
        {
          notesToUse.map(note => {
            return (
            <NoteItem key={note.id} note={note}>
              <ArchiveNote note={note}/>
              <UpdateNoteModal note={note}/>
              <DeleteNoteModal note={note} />
            </NoteItem>
          )})
        }
      </div>
    )
  } else {
    notesToUse = archivedNotes
    return (
      <div className="row row-cols-4 gx-4 gy-2">
        {
          notesToUse.map(note => {
            return (
            <NoteItem key={note.id} note={note}>
              <UnArchiveNote note={note}/>
              <UpdateNoteModal note={note}/>
              <DeleteNoteModal note={note} />
            </NoteItem>
          )})
        }
      </div>
    )
  }
}
