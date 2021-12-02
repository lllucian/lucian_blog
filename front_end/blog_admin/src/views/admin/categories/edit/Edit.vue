<template>
  <Form :title="$route.meta.title" :categoryId="$route.params.id"></Form>
</template>

<script lang="ts">
import {defineComponent} from "vue";
import Form from "/@/views/admin/categories/common/Form.vue"

export default defineComponent ({
  components: {
    Form
  },
  setup(){
  }
})
</script>

<style scoped>

</style>
