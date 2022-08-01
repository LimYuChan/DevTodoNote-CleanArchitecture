package com.devsurfer.domain.util

object Constants {
    const val CODE_UN_AUTHORIZE = 401
    const val CODE_BAD_REQUEST = 404

    const val APP_PREFERENCE_NAME = "dev_note_preference"
    const val PREFERENCE_KEY_ACCESS_TOKEN = "access_token"

    const val TOAST_ERROR_INTERNET_CONNECTED = "인터넷 연결을 확인해주세요."
    const val TOAST_ERROR_UNHANDLED ="알 수 없는 에러가 발생했습니다."
    const val TOAST_ERROR_NOT_FOUND = "정보를 찾을 수 없습니다."
    const val TOAST_ERROR_GET_ACCESS_TOKEN_BLANK = "토큰 발급 과정에서 문제가 발생했습니다.\n잠시후 다시 시도해주세요."
    const val TOAST_ERROR_UN_AUTHORIZE = "로그인 정보가 만료되었습니다. 다시 로그인 해주세요."

    const val FORMAT_RECEIVE_DATE_TIME = "yyyy-MM-DD'T'HH:mm:ss'Z'"
    const val FORMAT_CONVERT_DATE_TIME = "yyyy-MM-DD HH:mm"

    const val DEFAULT_NEW_BRANCH_TITLE = "WORK"
}