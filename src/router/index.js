import { createRouter, createWebHistory } from 'vue-router'
import AuthSection from '../components/AuthSection.vue'
import BookSection from '../components/BookSection.vue'
import BorrowPage from '../views/BorrowPage.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/auth' },
    { path: '/auth', component: AuthSection },
    { path: '/books', component: BookSection },
    { path: '/borrow', component: BorrowPage },
    { path: '/:pathMatch(.*)*', redirect: '/auth' }
  ]
})

export default router
