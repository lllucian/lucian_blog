<template>
  <Form :title="$route.meta.title" :user-id="$route.params.id"></Form>
</template>

<script lang="ts" setup>
import Form from "/@/views/admin/users/common/Form.vue"
</script>

