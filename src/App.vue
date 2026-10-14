<script setup>
import { onMounted } from 'vue'
import { useLibraryStore } from './stores/library'

import AuthSection from './components/AuthSection.vue'
import BookSection from './components/BookSection.vue'
import MemberSection from './components/MemberSection.vue'
import BorrowSection from './components/BorrowSection.vue'

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
    <p v-if="store.message" class="message-box">
      <b>{{ store.message }}</b>
    </p>

    <AuthSection />
    <BookSection />
    <MemberSection />
    <BorrowSection />
  </main>
</template>
