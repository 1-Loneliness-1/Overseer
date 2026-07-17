package com.home.core.network.api

sealed interface Request {

    data object GetServerStatusRequest : Request

}