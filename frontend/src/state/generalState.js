import {create} from 'zustand'

export const useListStore = create(set => ({
  noteList: [],
  categories: [],
  selectedFilter: "Filters...",
  setFilter: (filter) => {
    set(s => ({selectedFilter: filter}))
  },
  setList: (initList) => {
    set(s => {
      const ctgs = initList.map(note => [...note.categories]).flat().map(c => c.name)
      return ({noteList: initList, categories: [...ctgs]})
      
    })
  },
  addNewNote: (note) => {
    set(s => ({noteList: [...s.noteList, note]}))
  }, 
  deleteNote: (noteId) => {
    set(s => ({noteList: s.noteList.filter(n => n.id !== noteId)}))
  },
  updateList: (note) => {
    set(s => ({noteList: s.noteList.map(n => n.id === note.id ? note : n)}))
  }
}))