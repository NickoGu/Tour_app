package repositories

import User

object UserRepository {

    private val users = mutableListOf<User>()

    init {
        users.add(User("2022/12/10", "Kvnrot", "elpapu", "Kevin", "Rotela", 150000))
        users.add(User("2021/01/11", "AHOZ", "lock_password", "Aylen", "Hoz", 200000))
        users.add(User("2018/04/15", "Diegote", "@12345", "Diego", "Gonzalez", 120000))
        users.add(User("2022/12/10", "BRIAN_BAYARRI", "abc123", "Brian", "Bayarri", 3500000))
        users.add(User("2021/01/12", "root", "123", "Elias", "Guerra", 200000))
    }

    fun login(username: String, password: String): User? {
        return users.find { user -> user.nickName == username && user.password == password }
    }

    fun findUserByNickname(userNickName:String) : User? {

        return users.find { user -> user.nickName == userNickName}

    }

    fun findUserByPassword(userPassword:String) : User? {

        return users.find { user -> user.password == userPassword }

    }

    fun add(usuarioRegistrado: User) {
        users.add(usuarioRegistrado)
    }

    fun getById(id: Long): User {
        return users.find { it.id == id }!! //Nunca puede ser nulo porque sino no hubiese podido iniciar sesion
    }

}

