package io.least.case_management.ui.cases

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import io.least.case_management.data.CaseItem
import io.least.case_management.data.CaseListRepository
import io.least.case_management.viewmodel.CaseListConfig
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jivesoftware.smack.MessageListener
import org.jivesoftware.smack.android.AndroidSmackInitializer
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smackx.mam.MamManager
import org.jivesoftware.smackx.muc.MultiUserChat
import org.jivesoftware.smackx.muc.MultiUserChatManager
import org.jxmpp.jid.DomainBareJid
import org.jxmpp.jid.EntityBareJid
import org.jxmpp.jid.impl.JidCreate
import org.jxmpp.jid.parts.Localpart
import org.jxmpp.jid.parts.Resourcepart
import kotlin.random.Random

class CaseListViewModel(
    private val connection: XMPPTCPConnection,
    private val caseListRepository: CaseListRepository,
    private val config: CaseListConfig
) : ViewModel() {
    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<CaseListUiState>(CaseListUiState.Initial)

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<CaseListUiState> = _uiState


    // Get the MultiUserChatManager instance
    private lateinit var multiUserChatManager: MultiUserChatManager

    fun init(context: Context) {
        try {

            AndroidSmackInitializer.initialize(context)
            CoroutineScope(Dispatchers.Main).launch { _uiState.emit(CaseListUiState.Initial) }

            val defer = CoroutineScope(Dispatchers.IO).async {
                connection.connect()
                Log.d("TEST", "Lgging in....")
                connection.login()
                Log.d("TEST", "Creating MultiUserChatManager....")
                multiUserChatManager = MultiUserChatManager.getInstanceFor(connection)

                withContext(Dispatchers.Main) { _uiState.emit(CaseListUiState.Connected) }
            }
        } catch (t: Throwable) {
            Log.e("TEST", "Error -> ${t.stackTraceToString()}")
            CoroutineScope(Dispatchers.Main).launch { _uiState.emit(CaseListUiState.Error(t.stackTraceToString())) }
        }
    }

    fun fetchCases() {
        val defer = CoroutineScope(Dispatchers.IO).async {
            val joinedRooms = multiUserChatManager.joinedRooms
//            val fetchMyCases = caseListRepository.fetchMyCases("${config.serverUrl}/${config.profileId}")
//            if (fetchMyCases.isSuccessful) {
//                fetchMyCases.body()?.let {
//                    CoroutineScope(Dispatchers.Main).launch { _uiState.emit(CaseListUiState.UpdatedCaseList(it)) }
//                }
//            }
            // FIXME
            CoroutineScope(Dispatchers.Main).launch {
                _uiState.emit(CaseListUiState.UpdatedCaseList(listOf()))
            }
        }
    }

    fun createNewCase() {
        val defer = CoroutineScope(Dispatchers.IO).async {
            try {
                // xmppServiceGroupDomain is specific for group chat for eg ("muc_localhost")
                val mucJid: EntityBareJid =
                    JidCreate.entityBareFrom("Case-${Random.nextInt()}@conference.jabber.uk")

                Log.d("TEST", "Creating MultiUserChat....")
                // Get the multiuserchat instance
                val multiUserChat: MultiUserChat = multiUserChatManager.getMultiUserChat(mucJid)

                //userName can be your name by which you want to join the room
                //nickName is the name which will be shown on group chat
                val nickName = Resourcepart.from("coder_off")
                multiUserChat.create(nickName).makeInstant();

                Log.d("TEST", "Creating MultiUserChat config....")

                val mucEnterConfiguration = multiUserChat.getEnterConfigurationBuilder(nickName)
                    .requestNoHistory()
                    .build()

                if (!multiUserChat.isJoined) {
                    Log.d("TEST", "Joining....")
                    multiUserChat.join(mucEnterConfiguration)
                    Log.d("TEST", "Joined")
                }

                // For listening incoming message
                multiUserChat.addMessageListener { Log.d("TEST", "--> Message: ${it.body}") }
                // incomingMessageListener is implemented below.
            } catch (t: Throwable) {
                Log.e("TEST", t.stackTraceToString())
                CoroutineScope(Dispatchers.Main).launch { _uiState.emit(CaseListUiState.Error(t.stackTraceToString())) }
            }
        }
    }
}

sealed class CaseListUiState {
    object Initial : CaseListUiState()
    object Connected : CaseListUiState()
    object Empty : CaseListUiState()
    class Error(val errorString: String) : CaseListUiState()
    data class UpdatedCaseList(val cases: List<CaseItem>) : CaseListUiState()
}
