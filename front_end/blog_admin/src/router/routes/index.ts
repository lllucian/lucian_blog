import { FRONT_LAYOUT, ADMIN_LAYOUT } from "../constant";
import { AppRouteModule, AppRouteRecordRaw } from "/@/router/types";

export const LoginRoute: AppRouteRecordRaw = {
  name: "Login",
  path: "/login",
  component: () => import("/@/views/login/Login.vue"),
  meta: { title: "Lucian Blog后台管理系统登陆界面" },
};

export const FrontRoutes: AppRouteModule[] = [
  {
    name: 'Home',
    path: '/',
    component: FRONT_LAYOUT,
    redirect: '/home',
    meta: {title: "Lucian Blog", hiddenMenu: true},
    children: [
      {
        name: 'HomePage',
        path: '/home',
        component: () => import('/@/views/front/home/Index.vue'),
        meta: {
          title: 'Lucian Blog',
          menu: '首页'
        }
      },
      {
        name: 'PostDetail',
        path: '/post/:id',
        component: () => import('/@/views/front/post/Index.vue'),
        meta: {
          title: 'Lucian Blog',
          menu: '博客详情',
          hiddenMenu: true
        }
      }
    ]
  }
]

export const AdminRoutes: AppRouteModule[] = [
  {
    name: "AdminPost",
    path: "/admin",
    component: ADMIN_LAYOUT,
    redirect: '/admin/posts',
    meta: {
      title: "文章",
      hiddenMenu: false,
      icon: 'pixelarticons:article-multiple'
    },
    children: [
      {
        name: "AdminPostIndex",
        path: "/admin/posts",
        component: () => import("/@/views/admin/posts/index/Index.vue"),
        meta: {
          title: "文章列表",
          hiddenMenu: false,
          icon: 'pixelarticons:article',
        },
      },
      {
        name: "AdminPostNew",
        path: "/admin/post/new",
        component: () => import("/@/views/admin/posts/new/New.vue"),
        meta: {
          title: "新建文章",
          hiddenMenu: false,
          icon: 'jam:write',
        },
      },
      {
        name: 'AdminPostEdit',
        path: '/admin/post/:id',
        component: () => import("/@/views/admin/posts/edit/Edit.vue"),
        meta: {
          title: '编辑文章',
          hiddenMenu: true,
          alias: '/admin/posts'
        }
      }
    ],
  },
  {
    name: "AdminTopPosts",
    path: "/admin/top_posts",
    component: ADMIN_LAYOUT,
    meta: {
      title: "轮播图",
      hiddenMenu: false,
      icon: 'bx:carousel'
    },
    children: [
      {
        name: 'AdminTopPostIndex',
        path: '/admin/top_posts',
        component: () => import("/@/views/admin/top_posts/Index.vue"),
        meta: {
          title: '轮播图',
          hiddenMenu: false,
          icon: 'bx:carousel'
        }
      }
    ]
  },
  {
    name: "AdminCategory",
    path: "/admin/categories",
    component: ADMIN_LAYOUT,
    meta: {
      title: "分类",
      hiddenMenu: false,
      icon: 'carbon:categories',
    },
    children: [
      {
        name: "AdminCategoryIndex",
        path: "/admin/categories",
        component: () => import("/@/views/admin/categories/index/Index.vue"),
        meta: {
          title: "分类列表",
          hiddenMenu: false,
          icon: 'carbon:categories',
        },
      },
      {
        name: "AdminCategoryNew",
        path: "/admin/category/new",
        component: () => import("/@/views/admin/categories/new/New.vue"),
        meta: {
          title: "新建分类",
          hiddenMenu: false,
          icon: 'ic:outline-category',
        },
      },
      {
        name: "AdminCategoryEdit",
        path: "/admin/category/:id",
        component: () => import("/@/views/admin/categories/edit/Edit.vue"),
        meta: {
          title: "编辑分类",
          hiddenMenu: true,
          icon: 'jam:write',
          alias: '/admin/categories',
        },
      },
    ],
  },
  {
    name: "AdminTag",
    path: "/admin/tags",
    component: ADMIN_LAYOUT,
    meta: {
      title: "标签",
      hiddenMenu: false,
      icon: 'gis:tags',
    },
    children: [
      {
        name: "AdminTagIndex",
        path: "/admin/tags",
        component: () => import("/@/views/admin/tags/index/Index.vue"),
        meta: {
          title: "标签列表",
          hiddenMenu: false,
          icon: 'gis:tags',
        },
      },
      {
        name: "AdminTagNew",
        path: "/admin/tag/new",
        component: () => import("/@/views/admin/tags/new/New.vue"),
        meta: {
          title: "新建标签",
          hiddenMenu: false,
          icon: 'carbon:tag-edit',
        },
      },
      {
        name: "AdminTagEdit",
        path: "/admin/tag/:id",
        component: () => import("/@/views/admin/tags/edit/Edit.vue"),
        meta: {
          title: "编辑标签",
          hiddenMenu: true,
          icon: 'carbon:tag-edit',
          alias: '/admin/tags',
        },
      },
    ],
  },
  {
    name: "AdminLog",
    path: "/admin/sys_logs",
    component: ADMIN_LAYOUT,
    meta: {
      title: "日志管理",
      hiddenMenu: false,
      icon: 'icon-park-outline:log',
    },
    children: [
      {
        name: "AdminLogIndex",
        path: "/admin/sys_logs",
        component: () => import("/@/views/admin/sys_logs/index/Index.vue"),
        meta: {
          title: "日志列表",
          hiddenMenu: false,
          icon: 'icon-park-outline:log',
        },
      }
    ]
  },
  {
    name: 'AdminRoles',
    path: "/admin/roles",
    component: ADMIN_LAYOUT,
    meta: {
      title: "角色管理",
      hiddenMenu: false,
      icon: 'fa6-solid:arrows-down-to-people'
    },
    children: [
      {
        name: 'AdminRolesIndex',
        path: "/admin/roles",
        component: () => import("/@/views/admin/roles/index/Index.vue"),
        meta: {
          title: '角色列表',
          hiddenMenu: false,
          icon: 'fa6-solid:arrows-down-to-people'
        }
      },
      {
        name: 'AdminRoleCreate',
        path: "/admin/role/new",
        component: () => import("/@/views/admin/roles/new/New.vue"),
        meta: {
          title: '角色创建',
          hiddenMenu: false,
          icon: 'fa6-solid:arrows-down-to-people'
        }
      },
      {
        name: "AdminRoleEdit",
        path: "/admin/role/:id",
        component: () => import("/@/views/admin/roles/edit/Edit.vue"),
        meta: {
          title: "角色编辑",
          hiddenMenu: true,
          icon: 'fa6-solid:arrows-down-to-people',
          alias: "/admin/roles"
        },
      },
    ]
  },
  {
    name: 'AdminUsers',
    path: "/admin/users",
    component: ADMIN_LAYOUT,
    meta: {
      title: "用户管理",
      hiddenMenu: false,
      icon: 'fa6-solid:arrows-down-to-people'
    },
    children: [
      {
        name: 'AdminUsersIndex',
        path: "/admin/users",
        component: () => import("/@/views/admin/users/index/Index.vue"),
        meta: {
          title: '用户列表',
          hiddenMenu: false,
          icon: 'fa6-solid:arrows-down-to-people'
        }
      },
      {
        name: 'AdminUserCreate',
        path: "/admin/user/new",
        component: () => import("/@/views/admin/users/new/New.vue"),
        meta: {
          title: '用户创建',
          hiddenMenu: false,
          icon: 'fa6-solid:arrows-down-to-people'
        }
      },
      {
        name: "AdminUserEdit",
        path: "/admin/user/:id",
        component: () => import("/@/views/admin/users/edit/Edit.vue"),
        meta: {
          title: "用户编辑",
          hiddenMenu: true,
          icon: 'fa6-solid:arrows-down-to-people',
          alias: "/admin/users"
        },
      },
    ]
  }
];
// export const basicRoutes = [LoginRoute, ...FrontRoutes, ...AdminRoutes];
