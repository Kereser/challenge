import React from 'react'
import useNoteData from '../hooks/useNoteData'
import NoteList from '../components/NoteList';
import Navbar from '../components/Navbar';
import FilterNote from '../components/FilterNote';
import { useListStore } from '../state/generalState';

export default function MyNotes() {
  const { loading } = useNoteData()
  const noteList = useListStore(s => s.noteList)

  if (loading) {
    return <h1>Loading!!...</h1>
  }

  return (
    <div className='container'>
      <Navbar />
      <FilterNote notes={noteList} />
      <NoteList notes={noteList}/>
    </div>
  )
}
