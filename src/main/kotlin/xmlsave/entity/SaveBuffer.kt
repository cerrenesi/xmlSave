package xmlsave.entity

import javax.persistence.*

@Entity
@Table(schema = BaseEntity.schemaName, name = SaveBuffer.tableName)
data class SaveBuffer(

        @Id
        @Column(name = "BufferId")
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        override val id: Long,

        @Column(name = "UUID")
        val uuid: String,

        @Column(name = "xmlData")
        val xmlData: String


): BaseEntity(id) {

        companion object {
                const val tableName: String = "SaveBuffer"
        }

}