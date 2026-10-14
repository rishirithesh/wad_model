<script setup>
import { onMounted } from 'vue'
import { useLibraryStore } from './stores/library'

const store = useLibraryStore()

onMounted(() => {
  store.getAllBooks()
  store.getAllMembers()
  store.getAllBorrows()
})
</script>

<template>
  <main>
    <h1>Library Management</h1>
    <nav aria-label="Main navigation">
      <RouterLink to="/auth">Auth</RouterLink>
      <RouterLink to="/books">Books</RouterLink>
      <RouterLink to="/borrow">Borrow</RouterLink>
    </nav>

    <p v-if="store.message" class="message-box">
      <b>{{ store.message }}</b>
    </p>

    <RouterView />
  </main>
</template>
