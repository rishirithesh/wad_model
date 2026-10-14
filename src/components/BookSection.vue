<script setup>
import { ref } from 'vue'
import { useLibraryStore } from '../stores/library'

const store = useLibraryStore()

const book = ref({ title: '', author: '', copies: 1 })
const editId = ref('')

async function onSaveBook() {
  if (editId.value) {
    await store.updateBook(editId.value, book.value)
  } else {
    await store.addBook(book.value)
  }
  book.value = { title: '', author: '', copies: 1 }
  editId.value = ''
}

async function onEditBook(b) {
  // Uses getOneBook(id) from backend or pre-fills form
  const fetched = await store.getOneBook(b.id)
  if (fetched) {
    book.value = { title: fetched.title, author: fetched.author, copies: fetched.copies }
  } else {
    book.value = { title: b.title, author: b.author, copies: b.copies }
  }
  editId.value = b.id
}

function onDeleteBook(id) {
  store.deleteBook(id)
}
</script>

<template>
  <div class="section">
    <h2>2. Books</h2>
    <input v-model="book.title" placeholder="Title" />
    <input v-model="book.author" placeholder="Author" />
    <input v-model.number="book.copies" type="number" placeholder="Copies" />
    <button @click="onSaveBook">{{ editId ? 'Update' : 'Add' }} Book</button>

    <ul>
      <li v-for="b in store.books" :key="b.id">
        {{ b.title }} - {{ b.author }} - Copies: {{ b.copies }}
        <button @click="onEditBook(b)">Edit</button>
        <button @click="onDeleteBook(b.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>
