package StructuralPattern

import org.junit.Test

class ComplexSystemStore(private val filePath: String) {

    private val cache: HashMap<String, String>

    init {
        println("Reading Data From File: $filePath")
        cache = HashMap()
    }

    fun store(key: String, payload: String) {
        cache[key] = payload
    }

    fun read(key: String): String = cache[key] ?: ""

    fun commit() = println("Storing Cached Data: $cache to File $filePath")
}

data class User(val login: String)

class UserRepository {

    private val systemPreference = ComplexSystemStore("/data/default.prefs")

    fun save(user: User) {
        systemPreference.store("USER_KEY", user.login)
        systemPreference.commit()
    }

    fun findFirst(): User = User(systemPreference.read("USER_KEY"))
}

class FacadeTest {

    @Test
    fun Facade() {
        val userRepository = UserRepository()
        val user = User("CJ CORONA")
        userRepository.save(user)
        val resultUser = userRepository.findFirst()
        println("Found stored user: $resultUser")
    }
}