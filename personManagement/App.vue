<script setup>
    
    import {ref,onMounted} from 'vue'

    const persons=ref([])

    const id=ref('')
    const name=ref('')
    const college=ref('')

    const url="http://localhost:8080/person"

    const load=async()=>{
      persons.value=await(await fetch(url)).json()

    }

    const add=async()=>{
      await fetch(url,{
        method:'POST',
        headers:{'content-type':'application/json'},
        body:JSON.stringify({
          id:id.value,
          name:name.value,
          college:college.value
        })
      })

      id.value=name.value=college.value=''
      load()
    }

    const update=async()=>{
      await fetch(url +'/' +id.value,{
        method:'PUT',
        headers:{'content-type':'application/json'},
        body:JSON.stringify({
          id:id.value,
          name:name.value,
          college:college.value
        })
      })

      id.value=name.value=college.value=''
      load()
    }

    const remove=async(id)=>{
      await fetch(url+'/'+id,{
        method:'DELETE'
      })

      load()
    }

    onMounted(load)
</script>

<template>
  <h1>Person Management system</h1>

  Enter the id:
  <input type="text" v-model="id">
  Enter the name:
  <input type="text" v-model="name">
  Enter the college:
  <input type="text" v-model="college">

  <button @click="add">Add</button>


  <ul>
    <li v-for="p in persons" :key="p.id">
      {{ p.id }}--{{ p.name }}--{{ p.college }}

      <button @click="remove(p.id)">DELETE</button>
    </li>
  </ul>

  <button @click="update">update</button>
</template>