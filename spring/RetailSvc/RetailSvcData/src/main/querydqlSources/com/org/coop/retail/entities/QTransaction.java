package com.org.coop.retail.entities;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTransaction is a Querydsl query type for Transaction
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTransaction extends EntityPathBase<Transaction> {

    private static final long serialVersionUID = 781691666L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTransaction transaction = new QTransaction("transaction");

    public final DatePath<java.util.Date> actionDate = createDate("actionDate", java.util.Date.class);

    public final StringPath billNo = createString("billNo");

    public final QBranchMaster branchMaster;

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath createUser = createString("createUser");

    public final StringPath deleteInd = createString("deleteInd");

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath passingAuthInd = createString("passingAuthInd");

    public final StringPath passingAuthRemark = createString("passingAuthRemark");

    public final StringPath purpose = createString("purpose");

    public final NumberPath<Integer> tranId = createNumber("tranId", Integer.class);

    public final StringPath tranNo = createString("tranNo");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath updateUser = createString("updateUser");

    public final StringPath usedInd = createString("usedInd");

    public QTransaction(String variable) {
        this(Transaction.class, forVariable(variable), INITS);
    }

    public QTransaction(Path<? extends Transaction> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTransaction(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTransaction(PathMetadata<?> metadata, PathInits inits) {
        this(Transaction.class, metadata, inits);
    }

    public QTransaction(Class<? extends Transaction> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branchMaster = inits.isInitialized("branchMaster") ? new QBranchMaster(forProperty("branchMaster")) : null;
    }

}

