package com.home.core.model.api

sealed interface Request {

    data object GetServerStatusRequest : Request

}