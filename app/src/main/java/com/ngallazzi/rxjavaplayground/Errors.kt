package com.ngallazzi.rxjavaplayground


enum class Errors(val id: Int) {
    GENERIC_ERROR(R.string.generic_error),
    NO_COORDINATES_ARGUMENT_PROVIDED(R.string.no_coordinates_arguments_error),
    GENERIC_NETWORK_ERROR(R.string.generic_error)
}