package com.gp.common.constant

/**
 * 路由路径
 * 命名规则：/开头并且必须大于两级，/模块/分类/具体名称
 * 比如: /模块名称/组件[activity]/组件名称
 *       /模块名称/服务[service]/服务名称
 */


//********************跳转相关********************

/**
 * 初始化相关界面
 */
// 词书选择界面
const val INIT_ACTIVITY_DICTIONARY = "/init/activity/dictionary"

/**
 * 登录模块
 */
// 登录页面
const val LOGIN_ACTIVITY_LOGIN = "/login/activity/login"
// 注册页面
const val LOGIN_ACTIVITY_REGISTER = "/login/activity/register"
// 隐私协议界面
const val LOGIN_ACTIVITY_POLICY = "/login/activity/policy"


/**
 * 首页模块
 */
const val MAIN_ACTIVITY_HOME = "/main/activity/home"

/**
 * 学习界面
 */
const val LEARN_ACTIVITY_GAME = "/learn/activity/game"

const val LEARN_ACTIVITY_MATCH = "/learn/activity/match"

const val LEARN_ACTIVITY_NOTE = "/learn/activity/note"

const val LEARN_ACTIVITY_VOICE = "/learn/activity/voice"


/**
 * 我的界面
 */
const val USER_ACTIVITY_ABOUT = "/user/activity/about"

const val USER_ACTIVITY_COLLECTION = "/user/activity/collection"

const val USER_ACTIVITY_HISTORY = "/user/activity/history"

const val USER_ACTIVITY_USERINFO = "/user/activity/userinfo"

const val USER_ACTIVITY_language = "/user/activity/language"

const val USER_ACTIVITY_NOTIFICATION = "/user/activity/notification"

const val USER_ACTIVITY_WORDBOOK = "/user/activity/wordbook"



/**
 * 查词界面
 */
const val SEARCH_ACTIVITY_SEARCH = "/search/activity/search"

const val SEARCH_ACTIVITY_WORD = "/search/activity/word"


/**
 * 背单词界面
 */
const val RECITE_ACTIVITY_LOAD = "/recite/activity/load"

const val RECITE_ACTIVITY_RECITE = "/recite/activity/recite"




// *******************服务相关********************

/**
 * 主页模块-主页
 */
const val MAIN_SERVICE_HOME = "/main/service/home"

/**
 * 登陆模块-登陆
 */
const val LOGIN_SERVICE_LOGIN = "/login/service/login"


/**
 * 搜索模块-搜索
 */
const val SEARCH_SERVICE_SEARCH = "/search/service/search"

/**
 * 背单词模块-背单词
 */
const val RECITE_SERVICE_RECITE = "/recite/service/recite"


/**
 * 学习模块-学习
 */
const val LEARN_SERVICE_LEARN = "/learn/service/learn"

/**
 * 我的模块-用户
 */
const val USER_SERVICE_USER = "/user/service/user"