package ir.r3za13.kotlin_rz_boilerplate.usecase


//class PostNoteUseCase @Inject constructor(private val cloudRepository: CloudRepository)
//    : BaseUsecase<ResponseBody>() {

//    private lateinit var note: NotePostRequest
//    private lateinit var taskId: String
//    private var isCompletion: Boolean = false
//
//    fun setNoteToSend(taskId: String, note: NotePostRequest): PostNoteUseCase {
//        this.note = note
//        this.taskId = taskId
//        return this
//    }
//
//    fun isCompletion(isCompletion: Boolean): PostNoteUseCase {
//        this.isCompletion = isCompletion
//        return this
//    }
//
//    override fun createObservable(): Single<ResponseBody>
//    {
//        return Single.just()
////          ResponseBody  = if (isCompletion) {
////        cloudRepository.setJobCompleted(taskId, note)
////    } else {
////        cloudRepository.postNotesForTask(taskId, note)
//    }
//}
