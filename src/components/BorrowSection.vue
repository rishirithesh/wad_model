<script setup>
import { ref } from 'vue'
import { useLibraryStore } from '../stores/library'

const store = useLibraryStore()
const borrowForm = ref({ bookTitle: '', memberName: '' })

async function onBorrowBook() {
  await store.borrowBook(borrowForm.value)
  borrowForm.value = { bookTitle: '', memberName: '' }
}

function onReturnBook(id) {
  store.returnBook(id)
}

function onDeleteBorrow(id) {
  store.deleteBorrow(id)
}
</script>

<template>
  <div class="section">
    <h2>4. Borrow Records</h2>
    <input v-model="borrowForm.bookTitle" placeholder="Book title" />
    <input v-model="borrowForm.memberName" placeholder="Member name" />
    <button @click="onBorrowBook">Borrow</button>

    <ul>
      <li v-for="r in store.borrows" :key="r.id">
        {{ r.bookTitle }} - {{ r.memberName }} - {{ r.status }}
        <button @click="onReturnBook(r.id)">Return</button>
        <button @click="onDeleteBorrow(r.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>
