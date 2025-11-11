<template>
  <v-app>
    <v-layout>
    <!-- Header -->
    <v-app-bar app color="primary" dark>
      <v-btn icon @click="toggleMini">
        <v-icon>mdi-menu</v-icon>
      </v-btn>
      <v-toolbar-title v-show="!mini" >Dashboard</v-toolbar-title>
    </v-app-bar>
    

    <v-navigation-drawer
        expand-on-hover
        rail
        v-model="mini"
      >
      <!-- Menu Items -->
      <v-list>
        <v-list-item
          v-for="item in menuItems"
          :key="item.title"
          :to="item.to"
          link
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-title >{{ item.title }}</v-list-item-title>

        </v-list-item>

      </v-list>
      
      </v-navigation-drawer>
      

    <!-- Main Content -->
    <v-main>
      <v-container fluid>
        <router-view />
      </v-container>
    </v-main>

    
    </v-layout>
  </v-app>
</template>


<script setup lang="ts">
import { ref,shallowRef  } from 'vue'

const mini = ref(false)

const toggleMini = () => {
  mini.value = !mini.value
}



const menuItems = [
  { title: 'Dashboard',subtitle: 'Dashboard', to: '/', icon: 'mdi-view-dashboard' }
  
]
</script>



<style scoped>
.dashboard-drawer {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.mini-toggle {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%; /* Full height of drawer */
}
</style>


