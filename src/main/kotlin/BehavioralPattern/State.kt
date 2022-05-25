package BehavioralPattern

import junit.framework.Assert.*
import org.junit.Test


sealed class AuthorizationState

object Unauthorized : AuthorizationState()

class Authorized(val userName: String) : AuthorizationState()

class AuthorizationPresenter {

    private var state: AuthorizationState = Unauthorized

    val isAuthorized: Boolean
        get() = when (state) {
            is Authorized -> true
            is Unauthorized -> false
        }

    val userName: String
        get() {
            return when (val state = this.state) {
                is Authorized -> state.userName
                is Unauthorized -> "Unknown"
            }
        }

    fun loginUser(userName: String) {
        state = Authorized(userName)
    }

    fun logoutUser() {
        state = Unauthorized
    }

    override fun toString() = "User '$userName' is logged in: $isAuthorized"
}

class StateTest {

    @Test
    fun State() {
        val authorizationPresenter = AuthorizationPresenter()

        authorizationPresenter.loginUser("Admin")
        println(authorizationPresenter)
        assertTrue(authorizationPresenter.isAuthorized)
        assertEquals(authorizationPresenter.userName, "Admin")

        authorizationPresenter.logoutUser()
        println(authorizationPresenter)
        assertFalse(authorizationPresenter.isAuthorized)
        assertEquals(authorizationPresenter.userName, "Unknown")
    }
}