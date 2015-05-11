package com.tap.sportplay.dao.sql;

import android.content.ContentValues;
import android.database.Cursor;

import com.tap.sportplay.domain.*;

public class PlayerSqlDao<T extends Player> 
		extends AbstractSqlDao<T> implements ITableProperties {

	public static final String TABLE_NAME = "players";
	private static final String COLUMN_NAME = "nome";
	private static final String COLUMN_POSITION = "posicao";
	private static final String COLUMN_MENSALISTA = "mensalista";
	private static final String COLUMN_AVALIACAO_CHUTE = "chute";
	private static final String COLUMN_AVALIACAO_PASSE = "passe";
	private static final String COLUMN_AVALIACAO_DRIBLE = "drible";
	private static final String COLUMN_AVALIACAO_VISAOJOGO = "visaojogo";
	private static final String COLUMN_AVALIACAO_MARCACAO = "marcacao";
	private static final String COLUMN_AVALIACAO_PREPAROFISICO = "preparofisico";
		
	
	private static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + TABLE_NAME + " (" +
	    DBConstants.KEY_ID + " INTEGER PRIMARY KEY," +
	    COLUMN_NAME + DBConstants.TYPE_TEXT + DBConstants.COMMA_SEP +
	    COLUMN_POSITION + DBConstants.TYPE_INT + DBConstants.COMMA_SEP +
	    COLUMN_MENSALISTA + DBConstants.TYPE_BOOL + DBConstants.COMMA_SEP +
	    COLUMN_AVALIACAO_CHUTE + DBConstants.TYPE_INT + DBConstants.COMMA_SEP +
	    COLUMN_AVALIACAO_DRIBLE + DBConstants.TYPE_INT + DBConstants.COMMA_SEP +
	    COLUMN_AVALIACAO_MARCACAO + DBConstants.TYPE_INT + DBConstants.COMMA_SEP +
	    COLUMN_AVALIACAO_PASSE + DBConstants.TYPE_INT + DBConstants.COMMA_SEP +
	    COLUMN_AVALIACAO_PREPAROFISICO + DBConstants.TYPE_INT + DBConstants.COMMA_SEP +
	    COLUMN_AVALIACAO_VISAOJOGO + DBConstants.TYPE_INT +	    
	    " )";

	private static final String SQL_DELETE_ENTRIES =
	    "DROP TABLE IF EXISTS " + TABLE_NAME;
	
	
	public PlayerSqlDao(BaseSqlDao database){
		super(database, TABLE_NAME);
	}

	@Override
	public ContentValues ConvertObjectToContentValues(T entry){
		
		ContentValues values = new ContentValues();
		
		Player data = (Player)entry;
		Avaliacao avaliacao = data.getAvaliacao();
		
		values.put(DBConstants.KEY_ID, data.getId());
		values.put(COLUMN_NAME, data.getName());
		values.put(COLUMN_POSITION, data.getPosition().getValue());	
		values.put(COLUMN_AVALIACAO_CHUTE, avaliacao.getChute());
		values.put(COLUMN_AVALIACAO_DRIBLE, avaliacao.getDrible());
		values.put(COLUMN_AVALIACAO_MARCACAO, avaliacao.getMarcacao());
		values.put(COLUMN_AVALIACAO_PASSE, avaliacao.getPasse());
		values.put(COLUMN_AVALIACAO_PREPAROFISICO, avaliacao.getPreparoFisico());
		values.put(COLUMN_AVALIACAO_VISAOJOGO, avaliacao.getVisaoDeJogo());
		
		return values;
	}

	@Override
	protected String[] getFields() {

		return new String [] {
			DBConstants.KEY_ID,
			COLUMN_NAME,
			COLUMN_POSITION,
			COLUMN_AVALIACAO_CHUTE,
			COLUMN_AVALIACAO_DRIBLE,
			COLUMN_AVALIACAO_MARCACAO,
			COLUMN_AVALIACAO_PASSE,
			COLUMN_AVALIACAO_PREPAROFISICO,
			COLUMN_AVALIACAO_VISAOJOGO,
		};

		
	}

	@Override
	protected T ConvertCursorToObject(Cursor c) {

		T entity = (T) new Player();

		entity.setId(super.getCursorInt(c, DBConstants.KEY_ID));
		entity.setName(super.getCursorString(c, COLUMN_NAME));
		int res = super.getCursorInt(c, COLUMN_POSITION);
		//entity.setPosition(Position.valueOf(arg0));
		Avaliacao avaliacao = entity.getAvaliacao();
		avaliacao.setChute(super.getCursorInt(c, COLUMN_AVALIACAO_CHUTE));
		avaliacao.setDrible(super.getCursorInt(c, COLUMN_AVALIACAO_DRIBLE));
		avaliacao.setMarcacao(super.getCursorInt(c, COLUMN_AVALIACAO_MARCACAO));
		avaliacao.setPasse(super.getCursorInt(c, COLUMN_AVALIACAO_PASSE));
		avaliacao.setPreparoFisico(super.getCursorInt(c, COLUMN_AVALIACAO_PREPAROFISICO));
		avaliacao.setVisaoDeJogo(super.getCursorInt(c, COLUMN_AVALIACAO_VISAOJOGO));
				
		return entity;
	}
	
	
}
