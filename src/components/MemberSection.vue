<script setup>
import { ref } from 'vue'
import { useLibraryStore } from '../stores/library'

const store = useLibraryStore()
const member = ref({ name: '', email: '' })

async function onAddMember() {
  await store.addMember(member.value)
  member.value = { name: '', email: '' }
};

function onDeleteMember(id) {
  store.deleteMember(id)
}
</script>

<template>
  <div class="section">
    <h2>3. Members</h2>
    <input v-model="member.name" placeholder="Name" />
    <input v-model="member.email" placeholder="Email" />
    <button @click="onAddMember">Add Member</button>

    <ul>
      <li v-for="m in store.members" :key="m.id">
        {{ m.name }} - {{ m.email }}
        <button @click="onDeleteMember(m.id)">Delete</button>
      </li>
    </ul>
  </div>
</template>
