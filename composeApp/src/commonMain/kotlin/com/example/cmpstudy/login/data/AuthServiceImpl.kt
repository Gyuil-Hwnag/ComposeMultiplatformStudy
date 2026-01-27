package com.example.cmpstudy.login.data

import com.example.cmpstudy.login.domain.User
import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthServiceImpl(
    val auth: FirebaseAuth
) : AuthService {

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override val currentUserId: String
        get() = auth.currentUser?.uid.toString()

    override val isAuthenticated: Boolean
        get() = auth.currentUser != null && auth.currentUser?.isAnonymous == false

    override val currentUser: Flow<User> = auth.authStateChanged.map { user ->
        user?.let { User(user.uid, user.isAnonymous) } ?: User()
    }

    private suspend fun launchWithAwait(block: suspend () -> Unit) {
        scope.async { block() }.await()
    }

    override suspend fun authenticate(email: String, password: String) {
        launchWithAwait {
            auth.signInWithEmailAndPassword(email, password)
        }
    }

    override suspend fun createUser(email: String, password: String) {
        launchWithAwait {
            auth.createUserWithEmailAndPassword(email, password)
        }
    }

    override suspend fun signOut() {
        val isAnonymous = auth.currentUser?.isAnonymous == true
        if (isAnonymous) {
            auth.currentUser?.delete()
        }
        auth.signOut()
    }
}
