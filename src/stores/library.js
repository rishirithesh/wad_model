import { defineStore } from 'pinia'
import { ref } from 'vue'

const API = 'http://localhost:8080/api'

export const useLibraryStore = defineStore('library', () => {
  // State
  const message = ref('')
  const loggedIn = ref('')

  const books = ref([])
  const members = ref([])
  const borrows = ref([])

  // Helper for sending JSON requests
  function send(url, method, body) {
    return fetch(API + url, {
      method: method,
      headers: { 'Content-Type': 'application/json' },
      body: body ? JSON.stringify(body) : undefined
    })
  }

  // ==========================================
  // 1. AuthController (/api/auth)
  // ==========================================

  // POST /api/auth/register
  async function register(userData) {
    const res = await send('/auth/register', 'POST', userData)
    message.value = await res.text()
    return res.ok
  }

  // POST /api/auth/login
  async function login(userData) {
    const res = await send('/auth/login', 'POST', userData)
    message.value = await res.text()
    if (res.ok) {
      loggedIn.value = userData.username
    } else {
      loggedIn.value = ''
    }
    return res.ok
  }

  // ==========================================
  // 2. BookController (/api/books)
  // ==========================================

  // GET /api/books
  async function getAllBooks() {
    try {
      const res = await fetch(API + '/books')
      books.value = await res.json()
    } catch (err) {
      console.error(err)
    }
  }

  // GET /api/books/{id}
  async function getOneBook(id) {
    try {
      const res = await fetch(API + '/books/' + id)
      return await res.json()
    } catch (err) {
      console.error(err)
      return null
    }
  }

  // POST /api/books
  async function addBook(bookData) {
    await send('/books', 'POST', bookData)
    await getAllBooks()
  }

  // PUT /api/books/{id}
  async function updateBook(id, bookData) {
    await send('/books/' + id, 'PUT', bookData)
    await getAllBooks()
  }

  // DELETE /api/books/{id}
  async function deleteBook(id) {
    await send('/books/' + id, 'DELETE')
    await getAllBooks()
  }

  // ==========================================
  // 3. MemberController (/api/members)
  // ==========================================

  // GET /api/members
  async function getAllMembers() {
    try {
      const res = await fetch(API + '/members')
      members.value = await res.json()
    } catch (err) {
      console.error(err)
    }
  }

  // POST /api/members
  async function addMember(memberData) {
    await send('/members', 'POST', memberData)
    await getAllMembers()
  }

  // DELETE /api/members/{id}
  async function deleteMember(id) {
    await send('/members/' + id, 'DELETE')
    await getAllMembers()
  }

  // ==========================================
  // 4. BorrowController (/api/borrows)
  // ==========================================

  // GET /api/borrows
  async function getAllBorrows() {
    try {
      const res = await fetch(API + '/borrows')
      borrows.value = await res.json()
    } catch (err) {
      console.error(err)
    }
  }

  // POST /api/borrows
  async function borrowBook(borrowData) {
    await send('/borrows', 'POST', borrowData)
    await getAllBorrows()
  }

  // PUT /api/borrows/{id}/return
  async function returnBook(id) {
    await send('/borrows/' + id + '/return', 'PUT')
    await getAllBorrows()
  }

  // DELETE /api/borrows/{id}
  async function deleteBorrow(id) {
    await send('/borrows/' + id, 'DELETE')
    await getAllBorrows()
  }

  return {
    // State
    message,
    loggedIn,
    books,
    members,
    borrows,

    // AuthController functions
    register,
    login,

    // BookController functions
    getAllBooks,
    getOneBook,
    addBook,
    updateBook,
    deleteBook,

    // MemberController functions
    getAllMembers,
    addMember,
    deleteMember,

    // BorrowController functions
    getAllBorrows,
    borrowBook,
    returnBook,
    deleteBorrow
  }
})
