import { useEffect, useState } from 'react'
import noteService from '../services/note'
import { useListStore } from '../state/generalState'

function useNoteData() {
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const setList = useListStore(s => s.setList)

  useEffect(() => {
    setLoading(true)
    async function getData() {
      try {
        const data = await noteService.getInitialNotes()
        setList(data)
        setLoading(false)
      } catch (e) {
        setError(e.message, "Cannnot fetching notes.")
        setLoading(false)
      }
    }

    getData()

  }, [])

  return {loading, error}
}

export default useNoteData