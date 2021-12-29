package com.v1bottoni.retroapp.domain.mappers

import com.v1bottoni.retroapp.data.dto.ResponseUsersDTO
import com.v1bottoni.retroapp.domain.models.User

class UsersMapper: BaseMapper<ResponseUsersDTO.UserDTO, User>() {
    override fun mapFrom(from: ResponseUsersDTO.UserDTO): User {
        return User(
            id = from.id.trim(),
            title = from.title.trim(),
            firstName = from.firstName.trim(),
            lastName = from.lastName.trim(),
            picture = from.picture.trim()

        )
    }

    override fun mapFrom(from: List<ResponseUsersDTO.UserDTO>): List<User> {
        return from.map { mapFrom(it) }   }
}
