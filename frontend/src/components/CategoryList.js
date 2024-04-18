import React from 'react'

export default function CategoryList({categories, setCategories}) {
  const deleteCategory = (name, e) => {
    e.preventDefault();

    console.log(name, categories);

    setCategories(categories.filter(c => c !== name))
  }

  return categories.map(ctg => {
    return (
      <div key={ctg + 23} className='d-flex my-0'>
        <p className='fs-6 mb-0 me-2'>{ctg}</p>
        <button type="button" className="btn-close btn-sm" onClick={(e) => deleteCategory(ctg, e)}></button>
      </div>
    )
  })
}
