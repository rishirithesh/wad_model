<script setup>
import { ref } from 'vue'
import { useLibraryStore } from '../stores/library'

const store = useLibraryStore()
const user = ref({ username: '', password: '' })

function onRegister() {
  store.register(user.value)
}

function onLogin() {
  store.login(user.value)
}
</script>

<template>
  <div class="section">
    <h2>1. Register / Login</h2>
    <p v-if="store.loggedIn">Logged in as: <b>{{ store.loggedIn }}</b></p>
    <div>
      <input v-model="user.username" placeholder="Username" />
      <input v-model="user.password" type="password" placeholder="Password" />
      <button @click="onRegister">Register</button>
      <button @click="onLogin">Login</button>
    </div>
  </div>
</template>
