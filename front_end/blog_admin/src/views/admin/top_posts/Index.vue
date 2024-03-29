<template>
  <el-collapse model-value="DataForm" accordion>
    <el-collapse-item name="DataForm">
      <el-row>
        <el-col :span="24">
          <el-table :data="topPosts" border style="width: 100%" ref="dragTable">
            <el-table-column type="index" label="ID" width="50" align="center"></el-table-column>
            <el-table-column prop="postTitle" label="标题">
              <template #default="scope">
                <template v-if="scope.row.edit">
                  <el-select-v2 v-model="scope.row.tempObj.postId"
                                filterable remote :options="scope.row.postOptions"
                  :remote-method="(query) => editPostRemoteOptions(query, scope.row)"></el-select-v2>
                </template>
                <template v-else>
                  {{ scope.row.postTitle }}
                </template>
              </template>
            </el-table-column>
            <el-table-column prop="isHidden" label="是否隐藏" width="180" align="center">
              <template #default="scope">
                <template v-if="scope.row.edit">
                  <el-switch
                      v-model="scope.row.tempObj.isHidden"
                      size="large"
                      active-text="隐藏"
                      inactive-text="显示"
                  />
                </template>
                <template v-else>
                  <el-switch
                      v-model="scope.row.isHidden"
                      size="large"
                      active-text="隐藏"
                      inactive-text="显示"
                      :disabled="true"
                  />
                </template>

              </template>
            </el-table-column>
            <el-table-column
                align="center"
                label="拖动"
                width="80"
            >
              <Icon icon="ant-design:drag-outlined" :size="20" :style="{cursor: 'pointer'}"></Icon>
            </el-table-column>
            <el-table-column align="center" label="操作" width="160">
              <template #default="scope">
                <template v-if="scope.row.edit">
                  <el-button type="warning" @click="cancelEdit(scope.row)">
                    <Icon icon="fluent:calendar-cancel-16-regular" :size="18"></Icon>
                  </el-button>
                  <el-button type="primary" @click="confirmEdit(scope.row)">
                    <Icon icon="line-md:confirm-circle" :size="18"></Icon>
                  </el-button>
                </template>
                <template v-else>
                  <el-button type="default" @click="editMode(scope.row)">
                    <Icon icon="ant-design:edit-outlined" :size="18"></Icon>
                  </el-button>
                </template>
              </template>
            </el-table-column>
          </el-table>
          <el-button class="mt-4" style="width: 100%" @click="dialogVisible = true"
          >新建轮播图
          </el-button>
        </el-col>
      </el-row>
    </el-collapse-item>
    <el-dialog
        v-model="dialogVisible"
        title="新建轮播图"
        width="50%"
    >
      <el-form ref="dialogForm" :rules="formRules" :model="tempFormData" label-position="right">
        <el-form-item label="文章" prop="postId">
          <el-select-v2 v-model="tempFormData.postId" style="width: 240px"
                        filterable remote clearable :options="postOptions"
                        :remote-method="remoteMethod"
                        placeholder="请输入文章标题">
          </el-select-v2>
        </el-form-item>
        <el-form-item label="是否隐藏" prop="isHidden">
          <el-switch
              v-model="tempFormData.isHidden"
              size="large"
              active-text="隐藏"
              inactive-text="显示"
          />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="closeMethod(dialogForm)">关闭</el-button>
        <el-button type="primary" @click="confirmMethod(dialogForm)"
        >提交</el-button
        >
      </span>
      </template>
    </el-dialog>
  </el-collapse>

</template>
<script lang="ts" setup>
import {nextTick, onMounted, reactive, ref} from "vue";
import {getRequest, postRequest} from "/@/requests";
import {default as Icon} from "/@/components/common/basic/Icon.vue";
import {FormInstance} from "element-plus";
import Sortable from "sortablejs"

const tempFormData = reactive({
  postId: '',
  isHidden: false,
});

const topPosts = ref<any[]>([]);


const editMode = (async (row: any) => {
  row.edit = true;
  const data = await getRequest(`/admin/top_post/post_selected/${row.tempObj.postId}`);
  if (data.data) {
    row.postOptions.push(data.data);
  } else {
    row.tempObj.postId = '';
  }
  // 将其他行的编辑状态给取消
  topPosts.value.forEach((topPost: any) => {
    if (topPost == row) return;
    cancelEdit(topPost);
  });
});

const cancelEdit = ((row: any) => {
  row.edit = false;
  row.tempObj.postId = row.postId;
  row.tempObj.isHidden = row.isHidden;
  row.postOptions = [];
});

const confirmEdit = (async (row: any) => {
  await postRequest(`/admin/top_post/${row.id}`, row.tempObj);
  await loadData();
});

const dialogForm = ref<FormInstance>();


nextTick(() => {
  setSort()
});

const sortList = ref<any[]>([]);

const loadData = async () => {
  const data = await getRequest("/admin/top_posts");
  if (data.data) {
    topPosts.value = data.data;
    topPosts.value.forEach((v: any) => {
      v.tempObj = {
        postId: v.postId,
        isHidden: v.isHidden,
      };
      v.postOptions = [];
      v.edit = false;
    });
    data.data.forEach((topPost: any) => {
      sortList.value.push({id: topPost.id});
    });
  } else {
    topPosts.value = [];
    sortList.value = [];
  }
}

onMounted(async () => loadData());

const dragTable = ref();

const dialogVisible = ref(false);

const formRules = reactive({
  postId: [
    {required: true, message: '请选择文章'}
  ]
});

const postOptions = ref([]);

const editPostRemoteOptions = async(query: any, row: any) => {
  if (query !== '') {
    const data = await getRequest(`/admin/top_post/post_select/${row.id}`, {query: query});
    if (data && data.data) {
      row.postOptions = data.data;
    } else {
      row.postOptions = []
    }
  } else {
    row.postOptions = []
  }
}

const remoteMethod = async (query: string) => {
  if (query !== '') {
    const data = await postRequest("/admin/fetch/top_posts/posts", {title: query});
    if (data && data.data) {
      postOptions.value = data.data;
    } else {
      postOptions.value = []
    }
  } else {
    postOptions.value = []
  }
}

const confirmMethod = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate(async (valid, field) => {
    if (valid) {
      await postRequest("/admin/top_post", tempFormData);
      dialogVisible.value = false;
      await loadData();
    }
  });
};

const closeMethod = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
  dialogVisible.value = false;
}

const setSort = () => {
  const el = dragTable.value.$el.querySelectorAll('.el-scrollbar__view > table > tbody')[0];
  Sortable.create(el, {
        ghostClass: 'sortable-ghost',
        onEnd: evt => {
          if (typeof (evt.oldIndex) !== 'undefined' && typeof (evt.newIndex) !== 'undefined') {
            const targetRow = sortList.value.splice(evt.oldIndex, 1)[0];
            sortList.value.splice(evt.newIndex, 0, targetRow);
            postRequest("/admin/top_posts/drag_sort", sortList.value);
          }
        }
      }
  )
}

</script>
<style lang="scss">
.sortable-ghost {
  opacity: .8;
  color: #fff !important;
  background-color: #42b983 !important;
}
</style>
<style scoped>
.el-collapse {
  padding: 8px;
  background: white;
}

.el-tag + .el-tag {
  margin-left: 10px;
}
</style>