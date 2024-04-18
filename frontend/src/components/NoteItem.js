import React from 'react'

export default function NoteItem({note, children}) {

  return (
    <div className="col">
      <div className="card">
        <div className='card-header'>{note.title}</div>
        <div className="card-body">
          <p className="card-text">{note.updatedAt}</p>
        </div>
        <div className='card-footer d-flex flex-column flex-lg-row justify-content-end'>
          {children}
        </div>
      </div>
    </div>
  )
}
