<template>
  <v-container v-if="project">
    <h1>{{ project.name }}</h1>
    <p>{{ project.description }}</p>

    <div v-for="group in orderedGroups" :key="group.categoryId" class="mb-8">
      <v-divider class="my-4"></v-divider>
      <h2>{{ group.categoryName }}</h2>

      <v-row>
        <v-col
          v-for="personGroup in orderedPersonGroups(group.personRoleGroups)"
          :key="personGroup.personId"
          cols="12" sm="6" md="4"
        >
          <v-card class="pa-4">
            <v-row align="center" no-gutters>
              <v-avatar size="56" class="mr-4">
                <img :src="personGroup.pictureUrl" :alt="personGroup.fullName" />
              </v-avatar>
              <div>
                <div class="text-h6">{{ personGroup.fullName }}</div>
                <div v-for="role in orderedRoles(personGroup.roles)" :key="role.roleId" class="text-subtitle-2">
                  <strong>{{ role.roleName }}</strong> - {{ role.roleDescription }}
                </div>
              </div>
            </v-row>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </v-container>
  <v-progress-circular v-else indeterminate />
</template>

<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { useRoute } from 'vue-router'

const orderedGroups = computed(() => {
  if (!project.value || !project.value.roleCategoryGroups) {
    return []
  }
  return [...project.value.roleCategoryGroups].sort((a, b) => b.hierarchyLevel - a.hierarchyLevel)
})

const orderedRoles = (roles: RoleDto[]) =>
  [...roles].sort((a, b) => b.roleHierarchyLevel - a.roleHierarchyLevel)

const orderedPersonGroups = (personGroups: PersonRoleGroup[]) =>
  [...personGroups].sort((a, b) => {
    const maxLevelA = a.roles.length > 0 ? Math.max(...a.roles.map(r => r.roleHierarchyLevel)) : 0
    const maxLevelB = b.roles.length > 0 ? Math.max(...b.roles.map(r => r.roleHierarchyLevel)) : 0
    return maxLevelB - maxLevelA
  })

export interface ProjectDto {
  id: string; // UUID as string
  name: string;
  description: string;
  roleCategoryGroups: RoleCategoryGroup[];
}

export interface RoleCategoryGroup {
  categoryId: string; // UUID as string
  categoryName: string;
  hierarchyLevel: number;
  personRoleGroups: PersonRoleGroup[];
}

export interface PersonRoleGroup {
  personId: string; // UUID as string
  fullName: string;
  pictureUrl: string;
  roles: RoleDto[];
}

export interface RoleDto {
  roleId: string; // UUID as string
  roleName: string;
  roleDescription: string;
  roleHierarchyLevel: number;
}

const route = useRoute()
const project = ref<ProjectDto | null>(null)
let ws: WebSocket | null = null


onMounted(() => {
  const projectId = route.query.id as string
  const wsUrl = import.meta.env.VITE_APP_WS_URL || 'ws://localhost:8086/ws'
  ws = new WebSocket( wsUrl + '/projects/update')

ws.onmessage = (event: MessageEvent) => {
    try {
      const updatedProject = JSON.parse(event.data) as ProjectDto
      if (updatedProject.id === projectId) {
        project.value = updatedProject
      }
    } catch (err) {
      console.error('Failed to parse project JSON:', err)
    }
  }


  ws.onopen = () => {
    ws?.send(projectId)
  }

  ws.onclose = () => {
    console.log('WebSocket disconnected for project updates')
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