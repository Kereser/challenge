import React from 'react'
import { useListStore } from '../state/generalState'
import noteService from '../services/note'

export default function FilterNote({notes}) {
  const selectedFilter = useListStore(s => s.selectedFilter)
  const categories = useListStore(s => s.categories)

  if (!notes) {
    return (<h1>Loading</h1>)
  }

  const uniqueCategoryNames = ["All", ...new Set([...categories])]
  

  return (
    <div className='d-flex'>
      <p className='fs-4 me-3'>Filter notes by: </p>
      <div className="dropdown">
        <button className="btn btn-secondary dropdown-toggle btn-sm" type="button" data-bs-toggle="dropdown" aria-expanded="false">
          {selectedFilter}
        </button>
        <ul className="dropdown-menu">
          {
            uniqueCategoryNames.map(name => {
              return <Category key={name + name.length} name={name}/>
            })
          }
        </ul>
      </div>
    </div>
  )

}

function Category({name}) {
  const setList = useListStore(s => s.setList)
  const setFilter = useListStore(s => s.setFilter)
  
  const onBtnChange = async () => {
    setFilter(name)
    if (name === "All") {
      try {
        const res = await noteService.getInitialNotes()
        setList(res)
      } catch (error) {
        console.error(error)
      }
    } else {
      try {
        const res = await noteService.getInitialNotes(name)
        setList(res)
      } catch (error) {
        console.error(error)
      }
    }
  }
  return (<li><button className="dropdown-item" onClick={onBtnChange}>{name}</button></li>)
}
