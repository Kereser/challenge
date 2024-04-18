import React, { useEffect, useState } from 'react'
import { Link, useLocation } from 'react-router-dom'
import CreateNoteModal from './CreateNoteModal'

export default function Navbar() {
  const {search} = useLocation()
  const [title, setTitle] = useState("")
  const [linkName, setLinkName] = useState("")
  const [linkTo, setLinkTo] = useState("/notes")
  const [showCreate, setShowCreate] = useState(false)

  useEffect(() => {
    if (search !== "?archived=true") {
      setTitle("My notes")
      setLinkName("See archived")
      setLinkTo("/notes?archived=true")
      setShowCreate(true)
    } else {
      setTitle("Archived Notes")
      setLinkName("See my notes")
      setLinkTo("/notes")
      setShowCreate(false)
    }
  }, [search])

  
  return (
    <nav className="navbar bg-body-tertiary bg-light my-2 border rounded">
      <div className="container-fluid justify-content-between">
        <div className='d-flex align-items-center'>
        <p className='fs-1 mb-0 me-4'>{title}</p>
        {showCreate ? <CreateNoteModal /> : null}
        </div>
        <form className="d-flex" role="search">
          <Link className="navbar-brand" to={linkTo}>{linkName}</Link>
        </form>
      </div>
    </nav>
  )
}
