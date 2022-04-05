package qdu.java.recruit.constant;

public enum ResultCode {
    //成功状态码
    SUCCESS(200,"操作成功", true),
    FAILED(500, "未知错误", false),

    //参数错误 1001~1999
    PARAM_IS_INVALID(1001,"参数无效", false),
    PARAM_IS_BLANK(1002,"参数为空", false),
    PARAM_IS_TIME_OUT(1005, "参数失效", false),
    PARAM_TYPE_BIND_ERROR(1003,"参数类型错误", false),
    PARAM_NOT_COMPLETE(1004,"参数缺失", false),
    ENTRY_NOT_HAVE_ID(1004,"实体id缺失", false),
    ONE_CONFIG_IS_ON_USE(1005,"有配置正在使用", false),

    //用户错误 2001~2999
    USER_NOT_LOG_IN(2001,"用户未登录", false),
    USER_LOGIN_ERROR(2002,"用户名或密码错误", false),
    USER_ACCOUNT_FORBID(2003,"账户被禁用", false),
    USER_ACCOUNT_EXISTED(2008,"账户已经存在", false),
    USER_NOT_EXIST(2004,"用户不存在", false),
    USER_HAS_EXISTED(2005,"用户已存在", false),
    USER_NOT_HAVE_TOKEN(2006,"未检测到用户令牌", false),
    TOKEN_VALID_FAILED(2012,"用户token校验失败", false),
    EXPIRED_TOKEN(2007,"用户授权信息错误", false),
    INVALID_TOKEN_STRING(2008,"授权信息不合法", false),
    INVALID_USER(2009, "当前用户权限信息错误", false),
    PHONE_NUMBER_ALREADY_EXISTS(2010, "手机号已经存在", false),
    USER_NICKNAME_ALREADY_EXISTS(2011, "操作昵称已经存在", false),
    PHONE_NUMBER_NOT_EXISTS(2012, "操作手机号不存在", false),
    USER_NICKNAME_NOT_EXISTS(2011, "操作昵称不存在", false),
    STU_NUM_EXISTS(2013,"学号已经存在", false),
    STU_NUM_NOT_EXISTS(2013,"学号不存在", false),
    ACCOUNT_ERROR(2014,"用户不存在，账号密码错误，或账号被禁用", false),

    FILE_UPLOAD_FAILED(3001, "文件上传失败", false),
    FILE_STORAGE_FILED(3002, "文件存储失败", false),
    FILE_DOWNLOAD_FAILED(3003, "文件下载失败", false),
    FILE_DELETE_FAILED(3004, "文件删除失败", false),

    PERMISSION_REJECTED(4001, "无权访问该API", false),
    CONFIG_IS_NOT_SET(4002,"消息设置未打开该项消息通知", false),

    TASK_WEEK_EXISTS(5001, "该周次任务已经生成,重复操作", false),
    TASK_GROUP_WEEK_EXISTS(5002, "该周次任务组已经自动生成,请勿重复操作,如果需要重新生成请先清空该周次的任务组", false),
    TASK_GROUP_WEEK_GENERATE(5003, "该周次任务组无法自动生成,请检查是否已经没有生成该周任务或者该周已经没有课了", false),
    TASK_GROUP_WEEK_ALREADY_AUTO(5004, "该周次任务组已经自动排班,请勿重复操作,如果需要重新自动排班，请去撤销全部排班", false),
    TASK_GROUP_WEEK_ALREADY_Finish(5004, "该周次任务已经有人完成,禁止撤销排班", false),
    TASK_GROUP_WEEK_Number_Lack(5005, "该周次工作人数不足", false),

    SYSTEM_ERROR(500,"服务器异常", false),
    UPDATE_ERROR(500,"更新数据失败", false),
    INSERT_ERROR(500,"添加数据失败", false),
    DELETE_ERROR(500,"删除数据失败", false),

    /*配置错误*/
    NO_CURRENT_SEMESTER(6001,"暂无当前学期, 请联系管理员设置", false),
    NO_CURRENT_CONFIG(6001,"暂无获取的配置", false),
    ;


    private Integer code;
    private String message;
    private boolean success;

    ResultCode(Integer code, String message, boolean success) {
        this.message = message;
        this.code = code;
        this.success = success;
    }

    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }

    public void changerMessage(String message){
         this.message=message;
    }

    public boolean isSuccess() {
        return this.success;
    }
}
