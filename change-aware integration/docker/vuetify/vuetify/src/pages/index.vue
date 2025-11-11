<template>
  <v-container fluid>
    <v-row>
      <v-col v-for="card in cards" :key="card.title" cols="12" sm="6" md="4">
       <v-card @click="goToProject(card.title)" class="cursor-pointer">
          <v-card-title>{{ card.title }}</v-card-title>
          <v-card-text>{{ card.content }}</v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>



<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'

const cards = ref<{ title: string; content: string }[]>([])
const router = useRouter()
let ws: WebSocket | null = null
const wsUrl = import.meta.env.VITE_APP_WS_URL || 'ws://localhost:8086/ws'

const goToProject = (projectId: string) => {
  router.push({
    path: '/project',
    query: { id: projectId }
  })
}

onMounted(() => {
  ws = new WebSocket(wsUrl+'/projects')

  ws.onmessage = (event: MessageEvent) => {
    const key = event.data
    cards.value.push({ title: key, content: `Project ID: ${key}` })
  }

  ws.onopen = () => {
    console.log('WebSocket connection established')
  }

  ws.onclose = () => {
    console.log('WebSocket connection closed')
  }

  ws.onerror = (error) => {
    console.error('WebSocket error:', error)
  }
})

onUnmounted(() => {
  if (ws) {
    ws.close()
    ws = null
  }
})
</script>
